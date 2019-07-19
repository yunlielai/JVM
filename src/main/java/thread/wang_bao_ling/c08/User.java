package thread.wang_bao_ling.c08;

public class User {
 private String id;
 private Integer age;

 public User(String id, Integer age) {
  //this.id = new String(id);
  this.id = id;
  this.age = age;
 }

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

 public Integer getAge() {
  return age;
 }

 public void setAge(Integer age) {
  this.age = age;
 }
}
