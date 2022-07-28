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
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.Hashtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testLookUpDNUserWithAdminUser() throws Exception {
        // setup admin context
        Hashtable<String, String> environment = new Hashtable<String, String>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, "ldap://localhost:10390");
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");

        environment.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
        environment.put(Context.SECURITY_CREDENTIALS, "secret");

        DirContext adminContext = new InitialDirContext(environment);

        // prepare filters for user Marcelo Serpa
        String filter = "(&(objectClass=person)(cn=Marcelo Serpa))";

        String[] attrIDs = { "cn" };
        SearchControls searchControls = new SearchControls();
        searchControls.setReturningAttributes(attrIDs);
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        // lookup user on LDAP
        NamingEnumeration<SearchResult> searchResults = adminContext.search("dc=mserpa,dc=com", filter, searchControls);

        assertTrue(searchResults.hasMore());

        SearchResult result = (SearchResult) searchResults.next();
        Attributes attrs = result.getAttributes();

        String distinguishedName = result.getNameInNamespace();
        assertEquals(distinguishedName, "cn=Marcelo Serpa,ou=Users,dc=mserpa,dc=com");

        String commonName = attrs.get("cn").toString();
        assertEquals(commonName, "cn: Marcelo Serpa");


        adminContext.close();
    }

}