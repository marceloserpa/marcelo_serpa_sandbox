package com.marceloserpa.avro;


import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class SpecificRecordPoC {

    public static void main(String[] args) {

        Customer.Builder customerBuilder = Customer.newBuilder();
        customerBuilder.setAge(36);
        customerBuilder.setFirstName("Marcelo");
        customerBuilder.setLastName("Oi");
        customerBuilder.setHeight(175.5f);
        customerBuilder.setWeight(80f);
        Customer customer = customerBuilder.build();
        System.out.println(customer);

        DatumWriter<Customer> writer = new SpecificDatumWriter<>(Customer.class);
        try(DataFileWriter<Customer> dataFileWriter = new DataFileWriter<>(writer)) {
            dataFileWriter.create(customer.getSchema(), new File("customer-specific.avro"));
            dataFileWriter.append(customer);
        } catch (IOException e) {
            e.printStackTrace();
        }


        final File file = new File("customer-specific.avro");
        final DatumReader<Customer> datumReader = new SpecificDatumReader<>(Customer.class);

        DataFileReader<Customer> dataFileReader;
        try {
            dataFileReader = new DataFileReader<>(file, datumReader);
            while(dataFileReader.hasNext()) {
                Customer readCustomer = dataFileReader.next();
                System.out.println(readCustomer.toString());
                System.out.println("First name: " + readCustomer.getFirstName());
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

}
