package com.marceloserpa.sets;

import java.util.HashSet;
import java.util.Set;

public class Main {
    static void main() {

        Set<String> policies = Set.of("VIEW_BOOK", "EDIT_BOOK");
        Set<String> userPolicies = Set.of("VIEW_BOOK");

        Set<String> missingPermissions = new HashSet<>(policies);
        missingPermissions.removeAll(userPolicies);

        missingPermissions.forEach(System.out::println);
    }
}
