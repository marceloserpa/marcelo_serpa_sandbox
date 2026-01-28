package com.marceloserpa.enumxrecords.usingrecords;

public sealed interface Payment permits CreditCard, WireTransfer{
}
