package NGSoft.assignment.Shaytaskmanager.db;


public class AdminUser extends User{

    AdminUser(int ID, String name, String email, boolean isAdmin, boolean isActive, String Password) {
        super(ID, name, email, isAdmin, isActive, Password);
    }
}
