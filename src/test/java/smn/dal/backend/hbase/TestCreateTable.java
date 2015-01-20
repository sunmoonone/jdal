package smn.dal.backend.hbase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.conf.Configuration;

public class TestCreateTable extends TestCase{
	private static final byte[] FAMILY1 = Bytes.toBytes("cf1");
	private static final byte[] FAMILY2 = Bytes.toBytes("cf2");
	private static final byte[] QUALIFIER1 = Bytes.toBytes("c1");
	private static final byte[] QUALIFIER2 = Bytes.toBytes("c2");
	private static final byte[] QUALIFIER3 = Bytes.toBytes("c3");

	private Configuration conf;

	private HTable createTable(byte[] tableName)
			throws MasterNotRunningException, ZooKeeperConnectionException,
			IOException {
		HBaseAdmin admin = new HBaseAdmin(getConfiguration());
		try {
			HTableDescriptor htd = new HTableDescriptor(
					TableName.valueOf(tableName));
			HColumnDescriptor hcd = new HColumnDescriptor(FAMILY1);
			hcd.setMaxVersions(10);// Just setting 10 as I am not testing with
									// more than 10 versions here
			htd.addFamily(hcd);

			admin.createTable(htd, Bytes.toBytes(0), Bytes.toBytes(120), 5);
			HTable ht = new HTable(getConfiguration(), tableName);
			return ht;
		} finally {
			admin.close();
		}
	}

	private Configuration getConfiguration() {
		if (conf == null) {
			conf = HBaseConfiguration.create();
		}
		return conf;
	}

	public void testDeleteRows(){
		
	}

	public void testPut() throws IOException, InterruptedIOException {
		byte[] tableName = Bytes.toBytes("dalTable");
		HTable ht = createTable(tableName);
		List<Put> puts = new ArrayList<Put>(100);
		for (int j = 0; j < 100; j++) {
			byte[] rowkey = Bytes.toBytes(j);
			String value = (j % 10 == 0) ? "v1" : "v2";
			puts.add(createPut(rowkey, value));
		}
		ht.put(puts);
		Scan scan = new Scan();
		scan.addColumn(FAMILY1, QUALIFIER2);
		// Delete the column cf1:col2

		int rows = 0;
		for (Result result : ht.getScanner(new Scan())) {
			assertEquals(2, result.getFamilyMap(FAMILY1).size());
			assertTrue(result.getColumnCells(FAMILY1, QUALIFIER2).isEmpty());
			assertEquals(1, result.getColumnCells(FAMILY1, QUALIFIER1).size());
			assertEquals(1, result.getColumnCells(FAMILY1, QUALIFIER3).size());
			rows++;
		}
		assertEquals(100, rows);
		ht.close();
	}

	private Put createPut(byte[] rowkey, String value) {
		Put put = new Put(rowkey);
		put.add(FAMILY1, QUALIFIER1, value.getBytes());
		put.add(FAMILY1, QUALIFIER2, value.getBytes());
		put.add(FAMILY1, QUALIFIER3, value.getBytes());
		return put;
	}
}
