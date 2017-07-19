package com.example.demo.output;

import com.example.demo.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

/**
 * Created by 张彦辉 on 2017/7/18.
 */
@ApiModel
public class GetUsersOutput {

    @ApiModelProperty(value = "List of users", required = true)
    public List<UserOutput> users = new ArrayList<>();
    @ApiModelProperty(value = "Success or not", required = true)
    public boolean success = true;

    public GetUsersOutput(List<User> users) {
        /*
        There are two types of iterate the users. The difference between them is the former can call methods of element and can use
        iterator.remove() to remove the current element by using Iterator, while the latter can use i to make index-based calls to
        methods of list. Note that when the List is LinkedList, the latter's get(i) have a time complexity O(n), which is not good.
         */
        for (User user:users)
            this.users.add(new UserOutput(user));
//        for (int i = 0; i < users.size(); ++i)
//            this.users.add(new UserOutput(users.get(i)));
    }
}
