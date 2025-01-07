/** Represents a user in a social network. A user is characterized by a name,
 *  a list of user names that s/he follows, and the list's size. */
 public class User {

    // Maximum number of users that a user can follow
    static int maxfCount = 10;

    private String name;       // name of this user
    private String[] follows;  // array of user names that this user follows
    private int fCount;        // actual number of followees (must be <= maxfCount)

    /** Creates a user with an empty list of followees. */
    public User(String name) {
        this.name = name;
        follows = new String[maxfCount]; // fixed-size array for storing followees
        fCount = 0;                      // initial number of followees
    }

    /** Creates a user with some followees. The only purpose of this constructor is 
     *  to allow testing the toString and follows methods, before implementing other methods. */
    public User(String name, boolean gettingStarted) {
        this(name);
        follows[0] = "Foo";
        follows[1] = "Bar";
        follows[2] = "Baz";
        fCount = 3;
    }

    /** Returns the name of this user. */
    public String getName() {
        return name;
    }

    /** Returns the follows array. */
    public String[] getfFollows() {
        return follows;
    }

    /** Returns the number of users that this user follows. */
    public int getfCount() {
        return fCount;
    }

    /** If this user follows the given name, returns true; otherwise returns false. */
    public boolean follows(String name) {
        for (int i = 0; i < this.getfFollows().length; i++) {
            String name1 = this.getfFollows()[i];
            if (name1.equals(name)){
                return true;
            }
        }
        return false;
    }
    /** Makes this user follow the given name. If successful, returns true. 
     *  If this user already follows the given name, or if the follows list is full, does nothing and returns false; */
    public boolean addFollowee(String name) {
        if (this.follows(name)){
            return false;
        }
        for (int i = 0; i < this.getfFollows().length; i++){
            if (this.getfFollows()[i] == null){
                this.getfFollows()[i] = name;
                this.fCount++;
                return true;
            }
        }
        return false;
    }

    /** Removes the given name from the follows list of this user. If successful, returns true.
     *  If the name is not in the list, does nothing and returns false. */
    public boolean removeFollowee(String name) {
        if (!this.follows(name)){
            return false;
        }
        int index = 0;
        for (int i = 0; i < this.getfFollows().length; i++) {
            String name1 = this.getfFollows()[i];
            if (name1.equals(name)){
                index = i;
                break;
               }
            }
            String name1 = this.getfFollows()[index];
            while (this.getfFollows()[index+1] != null){
                this.getfFollows()[index] = this.getfFollows()[index+1];
                this.getfFollows()[index+1] = name1;
                index++;
        }
        this.getfFollows()[index] = null;
        return true;
    }

    /** Counts the number of users that both this user and the other user follow.
    /*  Notice: This is the size of the intersection of the two follows lists. */
    public int countMutual(User other) {
        int sum = 0;
        for (int i = 0; this.getfFollows()[i] != null; i++){
            if (other.follows(this.getfFollows()[i])){
                sum++;
            }
        } 
        return sum;
    }

    /** Checks is this user is a friend of the other user.
     *  (if two users follow each other, they are said to be "friends.") */
    public boolean isFriendOf(User other) {
        if (this.follows(other.getName()) && other.follows(this.getName())){
            return true;
        }
        return false;
    }
    /** Returns this user's name, and the names that s/he follows. */
    public String toString() {
        String ans = name + " -> ";
        for (int i = 0; i < fCount; i++) {
            ans = ans + follows[i] + " ";
        }
        return ans;
    }
}
