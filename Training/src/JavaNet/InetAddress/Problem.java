package JavaNet.InetAddress;

import Interfaces.IProblem;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Problem implements IProblem {
    @Override
    public void solve() {
        System.out.println("getLocalHost");
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(address);

        System.out.println("getAllByName");
        InetAddress addresses[] = null;
        try {
            addresses = InetAddress.getAllByName("ukr.net");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        for (InetAddress entry: addresses) {
            System.out.println(entry);
        }

        System.out.println("getByName");
        try {
            address = InetAddress.getByName("ukr.net");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(address);
        System.out.println("CanonicalHostName: " + address.getCanonicalHostName());
        System.out.println("HostAddress: " + address.getHostAddress());
        System.out.println("HostName: " + address.getHostName());
        System.out.println("isMulticastAddress: " + address.isMulticastAddress());


        System.out.println("getByAddress");
        byte[] bytes = {(byte)212,(byte)42,(byte)76,(byte)252};
        try {
            address = InetAddress.getByAddress(bytes);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(address);
        System.out.println("CanonicalHostName: " + address.getCanonicalHostName());
        System.out.println("HostAddress: " + address.getHostAddress());
        System.out.println("HostName: " + address.getHostName());
        System.out.println("isMulticastAddress: " + address.isMulticastAddress());
    }
}
