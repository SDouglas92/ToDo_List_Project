package ToDo_List;

public class Game implements Media{

  private String title;
  private String genre;

  public Game(String title, String genre){
    this.title = title;
    this.genre = genre;
  }

  public String title(){
    return this.title;
  }

  public String genre(){
    return this.genre;
  }
}