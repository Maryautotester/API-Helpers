package citrus.pojo.reqres;

import citrus.pojo.data.Rate;
import citrus.pojo.data.UserList;

public class GetResponse {
    public UserList getResponseUserList() {
        UserList userList = new UserList();

        userList.setName("Test user");
        userList.setCourse("QA");
        userList.setEmail("test@test.test");
        userList.setAge(23L);

        return userList;
    }
    public Rate getResponseRate() {
        Rate rate = new Rate();

        rate.setName("Test user");
        rate.setScore(78L);

        return rate;
    }








}
