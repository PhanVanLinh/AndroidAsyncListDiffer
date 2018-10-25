package toong.com.androidasynclistdiffer;

public class Contact implements Comparable {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(Object o) {
        Contact compare = (Contact) o;
        if (compare.getName().equals(this.name) && compare.getPhoneNumber().equals(this.phoneNumber)) {
            return 0;
        }
        return 1;
    }
}