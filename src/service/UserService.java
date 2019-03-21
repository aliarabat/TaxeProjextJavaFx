/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.User;

/**
 *
 * @author ALI
 */
public class UserService extends AbstractFacade<User>{
    
    public UserService() {
        super(User.class);
    }
    
    public int seConnecter(User user){
        User u=find(user.getId());
        if (u==null) {
            return -1;
        }else{
            if (!u.getPassword().equals(user.getPassword())) {
                return -2;
            }else{
                System.out.println("hello ali");
                return 1;
            }
        }
    }
}
