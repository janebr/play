package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import play.data.validation.Constraints.Email;

import java.security.*;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import play.api.libs.Crypto;


@Entity
public class User extends Model {

    @Id
    @Email
    @Constraints.Required(message = "Обязательное поле")
    private String email;

    private String passwordHash;

    private String name;

    private String phone;

    private String salt;

    public User(String email, String password, String name, String phone) {
        this.email = email;
        setPassword(password);
        this.name = name;
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }


    public String getPassword(){
        return passwordHash;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    private String getHash(String s) {
        return SHA256(s);
    }

    public void setPassword(String password) {
        this.salt = genSalt();
        this.passwordHash = getHash(password+salt);
    }

    private boolean checkPassword(String password) {
        return getHash(password+salt).equals(passwordHash);
    }

    public static String authenticate(String email, String password) {
        User user = find.byId(email);
        if (user == null || !user.checkPassword(password))
            return "Пользователь с данным email не зарегистрирован или не верный пароль";
        else
            return null;
    }

    public static boolean emailAvailable(String email) {
        return (find.byId(email) == null);
    }

    public static String SHA256(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(str.getBytes());
            return Base64.encodeBase64String(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String SHA1(String str){
        return Crypto.cookieSigner().sign(str);
    }

    public static String genSalt(){
        final Random r = new SecureRandom();
        byte[] salt = new byte[32];
        r.nextBytes(salt);
        return Base64.encodeBase64String(salt);
    }
    public static final Finder<String, User> find = new Finder<String, User>(User.class);
}