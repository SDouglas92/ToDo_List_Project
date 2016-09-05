import static org.junit.Assert.*;
import org.junit.*;
import ToDo_List.*;

public class GameTest {

  Game game;

  @Before 
  public void before(){
    game = new Game("Game", "Action");
  }

  @Test
  public void testTitle(){
    assertEquals("Game", game.title());
  }

  @Test
  public void testGenre(){
    assertEquals("Action", game.genre());
  }
}