package com.example.expiry_tracker.Model;
import com.example.expiry_tracker.Config.Config;

import java.sql.*;
import java.util.ArrayList;

public class UserAccount {
    private Connection conn;
    private String username;
    private String fullName;
    private String password;
    private String email;
    private String phone;


    public UserAccount() {
        this.username = "";
        this.fullName = "";
        this.password = "";
        this.email = "";
        this.phone = "";
    }

    public UserAccount(String username, String fullName, String password, String email, String phone) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }


    public String getUsername() {
        return username;
    }

    public String getFullName() { return fullName; }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() { return phone; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    // Login Validation
    public UserAccount validateLogin(String username, String password) {
        String query = "SELECT username, password, f_name, l_name, email WHERE username = ? AND password = ?";
        try {
            conn = new Config().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String fName = resultSet.getString("f_name");
                String lName = resultSet.getString("l_name");
                String email = resultSet.getString("email");

                return new UserAccount(username, password, fName, lName, email);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // Close the connection in a finally block to ensure it happens even if an exception occurs.
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the SQLException during closing.
            }
        }
    }


}



    // Create
    public boolean insertAccount(UserAccount newUser) {
        try {
            conn = new Config().getConnection();

            String query = "SELECT * FROM user WHERE username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, newUser.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return false;
            }else{
                query = "INSERT INTO user (username, fullname, pwd, email, phone) VALUES (?, ?, ?, ?, ?)";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, newUser.getUsername());
                preparedStatement.setString(2, newUser.getPassword());
                preparedStatement.setString(3, newUser.getFullName());
                preparedStatement.setString(4, newUser.getEmail());
                preparedStatement.setString(5, newUser.getPhone());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close the connection in a finally block to ensure it happens even if an exception occurs.
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the SQLException during closing.
            }
        }
    }
}

//
//    // Read (View)
//    public ArrayList<UserAccount> selectAll() {
//        ArrayList<UserAccount> userAccounts = new ArrayList<>();
//        String query = "SELECT username, password, f_name, l_name, email, profile_name, status FROM user_account INNER JOIN profile ON user_account.profile_id = profile.profile_id";
//        try {
//            conn = new DBConfig().getConnection();
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()) {
//                String username = resultSet.getString("username");
//                String password = resultSet.getString("password");
//                String fName = resultSet.getString("f_name");
//                String lName = resultSet.getString("l_name");
//                String email = resultSet.getString("email");
//                String profileName = resultSet.getString("profile_name");
//                boolean status = resultSet.getBoolean("status");
//                UserProfile userProfile = new UserProfile(profileName);
//                UserAccount userAccount = new UserAccount(username, password, fName, lName, email, userProfile, status);
//                userAccounts.add(userAccount);
//            }
//            return userAccounts;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            // Close the connection in a finally block to ensure it happens even if an exception occurs.
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                // Handle the SQLException during closing.
//            }
//        }
//    }
//
//    // Update
//    public boolean updateUserAccount(UserAccount updatedUser) {
//
//        try {
//
//            String query;
//            conn = new DBConfig().getConnection();
//            PreparedStatement preparedStatement;
//            if (updatedUser.getMax_slot() > 0 && updatedUser.getRole_id() > 0) {
//                query = "UPDATE user_account SET max_slot = ?, role_id = ? WHERE username = ?";
//                preparedStatement = conn.prepareStatement(query);
//                preparedStatement.setInt(1, updatedUser.getMax_slot());
//                preparedStatement.setInt(2, updatedUser.getRole_id());
//                preparedStatement.setString(3, updatedUser.getUsername());
//            } else {
//                query = "UPDATE user_account SET password = ?, f_name = ?, l_name = ?, email = ?, profile_id = ? WHERE username = ?";
//                preparedStatement = conn.prepareStatement(query);
//                preparedStatement.setString(1, updatedUser.getPassword());
//                preparedStatement.setString(2, updatedUser.getFirstName());
//                preparedStatement.setString(3, updatedUser.getLastName());
//                preparedStatement.setString(4, updatedUser.getEmail());
//                preparedStatement.setInt(5, updatedUser.getUserProfile().getProfileID()); // Set profile_id
//                preparedStatement.setString(6, updatedUser.getUsername());
//            }
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if(updatedUser.getUserProfile().getProfileID() != 4) {
//                query = "DELETE FROM bid WHERE username = ?";
//                preparedStatement = conn.prepareStatement(query);
//                preparedStatement.setString(1, updatedUser.getUsername());
//                preparedStatement.executeUpdate();
//            }
//
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            // Close the connection in a finally block to ensure it happens even if an exception occurs.
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                // Handle the SQLException during closing.
//            }
//        }
//    }
//
//    // Suspend
//    public boolean suspendUserAccount(String username) {
//        String query = "UPDATE user_account SET status = 0 WHERE username = ?";
//        try {
//            conn = new DBConfig().getConnection();
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            preparedStatement.setString(1, username);
//            int rowsAffected = preparedStatement.executeUpdate();
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            // Close the connection in a finally block to ensure it happens even if an exception occurs.
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                // Handle the SQLException during closing.
//            }
//        }
//    }
//
//    // Unsuspend
//    public boolean unsuspendUserAccount(String username) {
//        String query = "UPDATE user_account SET status = 1 WHERE username = ?";
//        try {
//            conn = new DBConfig().getConnection();
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            preparedStatement.setString(1, username);
//            int rowsAffected = preparedStatement.executeUpdate();
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            // Close the connection in a finally block to ensure it happens even if an exception occurs.
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                // Handle the SQLException during closing.
//            }
//        }
//    }
//
//    // Search
//    public ArrayList<UserAccount> getUserAccountByUsername(String search) {
//        ArrayList<UserAccount> userAccounts = new ArrayList<>();
//        String query = "SELECT username, password, f_name, l_name, email, profile_name, status FROM user_account INNER JOIN profile ON user_account.profile_id = profile.profile_id WHERE username LIKE ?";
//        try {
//            conn = new DBConfig().getConnection();
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            preparedStatement.setString(1, search + "%");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()) {
//                String username = resultSet.getString("username");
//                String password = resultSet.getString("password");
//                String fName = resultSet.getString("f_name");
//                String lName = resultSet.getString("l_name");
//                String email = resultSet.getString("email");
//                String profileName = resultSet.getString("profile_name");
//                boolean status = resultSet.getBoolean("status");
//                UserProfile userProfile = new UserProfile(profileName);
//                UserAccount userAccount = new UserAccount(username, password, fName, lName, email, userProfile, status);
//                userAccounts.add(userAccount);
//            }
//            return userAccounts;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            // Close the connection in a finally block to ensure it happens even if an exception occurs.
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                // Handle the SQLException during closing.
//            }
//        }
//    }
//
//    // Filter
//    public ArrayList<UserAccount> selectByProfileName(String profileName) {
//        ArrayList<UserAccount> userAccounts = new ArrayList<>();
//        String query = "SELECT username, password, f_name, l_name, email, profile_name, status FROM user_account INNER JOIN profile ON user_account.profile_id = profile.profile_id WHERE profile_name = ?";
//        try {
//            conn = new DBConfig().getConnection();
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            preparedStatement.setString(1, profileName);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()) {
//                String username = resultSet.getString("username");
//                String password =resultSet.getString("password");
//                String fName = resultSet.getString("f_name");
//                String lName = resultSet.getString("l_name");
//                String email = resultSet.getString("email");
//                String profile = resultSet.getString("profile_name");
//                boolean status = resultSet.getBoolean("status");
//                UserProfile userProfile = new UserProfile(profile);
//                UserAccount userAccount = new UserAccount(username, password, fName, lName, email, userProfile, status);
//                userAccounts.add(userAccount);
//            }
//            return userAccounts;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            // Close the connection in a finally block to ensure it happens even if an exception occurs.
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                // Handle the SQLException during closing.
//            }
//        }
//    }
//
//    public UserAccount getSelectedAccount(String username) {
//        String query = "SELECT * FROM user_account WHERE username = ?";
//        try {
//            conn = new DBConfig().getConnection();
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            preparedStatement.setString(1, username);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            UserAccount userAccount = null;
//            while(resultSet.next()) {
//                String password = resultSet.getString("password");
//                String firstName = resultSet.getString("f_name");
//                String lastName = resultSet.getString("l_name");
//                String email = resultSet.getString("email");
//                int profileID = resultSet.getInt("profile_id");
//                userAccount = new UserAccount(username, password, firstName, lastName, email, new UserProfile(profileID));
//            }
//            return userAccount;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            // Close the connection in a finally block to ensure it happens even if an exception occurs.
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                // Handle the SQLException during closing.
//            }
//        }
//    }
//
//    // Profile dropdown
//    public ArrayList<String> getProfileByName() {
//        String query = "SELECT profile_name FROM profile";
//        ArrayList<String> profileNameList = new ArrayList<>();
//
//        try {
//            conn = new DBConfig().getConnection();
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()) {
//                String profileName = resultSet.getString("profile_name");
//                profileNameList.add(profileName);
//            }
//            return profileNameList;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            // Close the connection in a finally block to ensure it happens even if an exception occurs.
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                // Handle the SQLException during closing.
//            }
//        }
//    }
//
//    // Role dropdown
//    public ArrayList<String> getRoleByName() {
//        String query = "SELECT role_name FROM role";
//        ArrayList<String> roleNameList = new ArrayList<>();
//
//        try {
//            conn = new DBConfig().getConnection();
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()) {
//                String roleName = resultSet.getString("role_name");
//                roleNameList.add(roleName);
//            }
//            return roleNameList;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            // Close the connection in a finally block to ensure it happens even if an exception occurs.
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                // Handle the SQLException during closing.




