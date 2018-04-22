import org.apache.hadoop.hbase.{CellUtil, TableName}
import org.scalatest.{BeforeAndAfterAll, FlatSpec}
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.filter.PageFilter

class SetSpec extends FlatSpec with BeforeAndAfterAll {

  var hbaseClient = null
  val hbaseminicluster = new HBaseTestHelper()

  override def beforeAll(): Unit = {
    hbaseminicluster.setup
  }

  override def afterAll(): Unit = {
    hbaseminicluster.cleanup
  }

  behavior of "Hbase minicluster"

  it should "be able to create new tables" in {
    // Create table
    val tableName = TableName.valueOf("default", "new_table")
    val tableDesBuilder = TableDescriptorBuilder.newBuilder(tableName)
    val columnDescBuilder = ColumnFamilyDescriptorBuilder.newBuilder("C".getBytes())
    columnDescBuilder.setMaxVersions(10)

    tableDesBuilder.addColumnFamily(columnDescBuilder.build())

    val tableDes = tableDesBuilder.build()
    val admin = hbaseminicluster.hbaseConn.getAdmin
    admin.createTable(tableDes)
    admin.listTableNames().foreach(println(_))


    println(admin.getRegions(tableName).size())
    // put data into table
    val testTable = hbaseminicluster.hbaseConn.getTable(tableName)

    for (ind <- 1 to 3) {
      // val put = new Put(("row" + ind).getBytes())
      val put = new Put("row".getBytes())
      put.addColumn("C".getBytes(), "column".getBytes(), 1524382623824L - ind * 10, "cell value".getBytes())
      Thread.sleep(1000)
      testTable.put(put)
    }

    testTable.getDescriptor

    // get data from the table
    val filter = new PageFilter(2)
    val scan = new Scan()
    scan.setFilter(filter)
    scan.readAllVersions()
    val scanner = testTable.getScanner(scan).iterator()
    while (scanner.hasNext()) {
      val result = scanner.next()
      val rowKey = result.getRow.map(_.toChar).mkString("")
      val cells = result.listCells()
      println(f"Cell count: ${cells.size()}")
      cells.forEach(cell => {
        val qualifier = new String(CellUtil.cloneQualifier(cell))
        val timestamp = cell.getTimestamp.toString
        val value = new String(CellUtil.cloneValue(cell))
        println(f"rowKey: $rowKey, qualifier: $qualifier, timestamp: $timestamp, value: $value")
      })
    }
  }
  it should "produce NoSuchElementException when head is invoked" in {
    intercept[NoSuchElementException] {
      Set.empty.head
    }
  }
}