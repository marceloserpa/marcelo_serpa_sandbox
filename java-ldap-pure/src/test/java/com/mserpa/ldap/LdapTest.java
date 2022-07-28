package com.mserpa.ldap;


import org.apache.directory.server.annotations.CreateLdapServer;
import org.apache.directory.server.annotations.CreateTransport;
import org.apache.directory.server.core.annotations.ApplyLdifFiles;
import org.apache.directory.server.core.annotations.CreateDS;
import org.apache.directory.server.core.annotations.CreatePartition;
import org.apache.directory.server.core.integ.AbstractLdapTestUnit;
import org.apache.directory.server.core.integ.FrameworkRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@RunWith(FrameworkRunner.class)
@CreateLdapServer(transports = { @CreateTransport(protocol = "LDAP", address = "localhost", port = 10390)})
@CreateDS(
        allowAnonAccess = false, partitions = {@CreatePartition(name = "TestPartition", suffix = "dc=mserpa,dc=com")})
@ApplyLdifFiles({"users.ldif"})
public class LdapTest extends AbstractLdapTestUnit {

    @Test
    public void testWithValidUser() throws Exception {
        Hashtable<String, String> environment = new Hashtable<String, String>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, "ldap://localhost:10390");
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");

        environment.put(Context.SECURITY_PRINCIPAL, "cn=Marcelo Serpa,ou=Users,dc=mserpa,dc=com");
        environment.put(Context.SECURITY_CREDENTIALS, "12345");

        DirContext context = new InitialDirContext(environment);
        context.close();
    }

    @Test(expected = AuthenticationException.class)
    public void testWithInvalidUser() throws Exception {
        Hashtable<String, String> environment = new Hashtable<String, String>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, "ldap://localhost:10390");
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");

        environment.put(Context.SECURITY_PRINCIPAL, "cn=Marcelo Serpssss,ou=Users,dc=mserpa,dc=com");
        environment.put(Context.SECURITY_CREDENTIALS, "12345");

        DirContext context = new InitialDirContext(environment);
        context.close();
    }

}