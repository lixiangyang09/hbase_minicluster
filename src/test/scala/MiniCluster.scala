import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseTestingUtility
import org.apache.hadoop.hbase.client._



class HBaseTestHelper {
  val hbaseMock = new HBaseTestingUtility
  var hbaseConf: Configuration = _
  var hbaseConn: Connection = _
  val versions: Int = 16


  def setup: Unit = {
    // hbaseMock.getConfiguration.set("hbase.zookeeper.quorm", "localhost")
    // this is used to setup multi cluser in single machine.

    hbaseMock.getConfiguration.set("hbase.master.info.port", "-1")
    hbaseMock.getConfiguration.set("hbase.regionserver.info.port", "-1")
    hbaseMock.startMiniCluster
    hbaseConf = hbaseMock.getConfiguration
    hbaseConn = ConnectionFactory.createConnection(hbaseConf)
  }

  def cleanup: Unit = {
    hbaseConn.close
    hbaseMock.shutdownMiniCluster
  }

}