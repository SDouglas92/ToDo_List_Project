import static org.junit.Assert.*;
import org.junit.*;
import ToDo_List.*;
import java.util.*;


public class MediaCollectionTest {

  MediaCollection media;

  @Before
  public void before(){
    this.media = new MediaCollection();
  }

  @Test
  public void listStartsEmpty(){
    assertEquals(0, media.mediaSize());
  }

  @Test 
  public void canAddToList(){
    Game game = new Game("Game", "Action");
    media.addMedia(game);
    assertEquals(1, media.mediaSize());
  }
}