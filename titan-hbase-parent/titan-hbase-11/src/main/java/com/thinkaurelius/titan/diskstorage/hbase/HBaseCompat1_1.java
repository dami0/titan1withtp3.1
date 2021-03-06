package com.thinkaurelius.titan.diskstorage.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.io.compress.Compression;

public class HBaseCompat1_1 implements HBaseCompat {

    @Override
    public void setCompression(HColumnDescriptor cd, String algo) {
        cd.setCompressionType(Compression.Algorithm.valueOf(algo));
    }

    @Override
    public HTableDescriptor newTableDescriptor(String tableName) {
        TableName tn = TableName.valueOf(tableName);
        return new HTableDescriptor(tn);
    }

    @Override
    public ConnectionMask createConnection(Configuration conf) throws IOException
    {
        return new HConnection1_1(ConnectionFactory.createConnection(conf));
    }

    @Override
    public void addColumnFamilyToTableDescriptor(HTableDescriptor tdesc, HColumnDescriptor cdesc)
    {
        tdesc.addFamily(cdesc);
    }

    @Override
    public void setTimestamp(Delete d, long timestamp)
    {
        d.setTimestamp(timestamp);
    }

}
