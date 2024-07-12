// package MediSync.MediSync_Model;

// import java.util.List;

// public class UserDataManagerTest {
//     public static void main(String[] args) {
//         UserDataManager userManager = new UserDataManager();

//         // Create test users
//         UserData user1 = new UserData("john.doe@example.com", "password123", "1234567890", "01/01/1990", "Male", "123 Main St");
//         UserData user2 = new UserData("jane.smith@example.com", "securepass", "0987654321", "02/02/1992", "Female", "456 Elm St");

//         // Add users
//         userManager.addUser(user1);
//         userManager.addUser(user2);

//         // Retrieve and print all users
//         List<UserData> users = userManager.getAllUsers();
//         System.out.println("All Users:");
//         for (UserData user : users) {
//             System.out.println(user.getEmail() + " - " + user.getPhoneNumber());
//         }

//         // Update user1's phone number
//         user1.setPhoneNumber("1111111111");
//         userManager.addUser(user1);

//         // Print updated users
//         users = userManager.getAllUsers();
//         System.out.println("Updated Users:");
//         for (UserData user : users) {
//             System.out.println(user.getEmail() + " - " + user.getPhoneNumber());
//         }

//         // Delete user2
//         userManager.removeUser("jane.smith@example.com");

//         // Print users after deletion
//         users = userManager.getAllUsers();
//         System.out.println("Users After Deletion:");
//         for (UserData user : users) {
//             System.out.println(user.getEmail() + " - " + user.getPhoneNumber());
//         }
//     }
// }
