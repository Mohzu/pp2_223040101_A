/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Moch Zuhdi Maulana N
 */
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.List;
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<User> getAllUsers(); 

    @Insert("INSERT INTO users (name,email) VALUES (#{name}, #{email})")
    void insertUser(User user);

}