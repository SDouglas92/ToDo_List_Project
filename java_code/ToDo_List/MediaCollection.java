package ToDo_List;
import java.util.*;

public class MediaCollection{

  private ArrayList<Media> list;

  public MediaCollection(){
    this.list = new ArrayList<Media>();
  }

  public int mediaSize(){
    return list.size();
  }

  public void addMedia(Media media){
    list.add(media);
  }
}