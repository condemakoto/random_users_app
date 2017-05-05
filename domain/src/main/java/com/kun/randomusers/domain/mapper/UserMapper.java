package com.kun.randomusers.domain.mapper;

import com.kun.randomusers.data.entity.UserPageEntity;
import com.kun.randomusers.data.entity.UsersEntity;
import com.kun.randomusers.domain.model.Location;
import com.kun.randomusers.domain.model.User;
import com.kun.randomusers.domain.model.UsersListPage;

import java.util.ArrayList;

/**
 * @author Julio Kun
 * @version 0.1
 * <p>
 *     Mapper that transform my user entity to the user model.
 * </p>
 */

public class UserMapper {

    public UsersListPage map(UserPageEntity entity) {
        UsersListPage userPage = new UsersListPage();

        userPage.setPage(entity.getInfo().getPage());
        userPage.setSeed(entity.getInfo().getSeed());
        ArrayList<User> users = new ArrayList<>();
        for (UsersEntity userEntity : entity.getResults()) {
            users.add(mapUser(userEntity));
        }
        userPage.setUsers(users);

        return userPage;
    }

    private User mapUser(UsersEntity userEntity) {
        User user = new User();
        user.setTitle(userEntity.getName().getTitle());
        user.setFirstName(userEntity.getName().getFirst());
        user.setLastName(userEntity.getName().getLast());
        user.setGender(userEntity.getGender());
        user.setEmail(userEntity.getEmail());
        user.setPhone(userEntity.getPhone());
        user.setUsername(userEntity.getLogin().getUsername());

        Location location = new Location();
        location.setStreet(userEntity.getLocation().getStreet());
        location.setCity(userEntity.getLocation().getCity());
        location.setPostCode(userEntity.getLocation().getPostCode());
        location.setState(userEntity.getLocation().getState());
        user.setLocation(location);

        user.setThumbnailImageUrl(userEntity.getPicture().getThumbnail());
        user.setBigImageUrl(userEntity.getPicture().getLarge());

        return user;
    }
}
