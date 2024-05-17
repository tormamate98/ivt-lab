package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  TorpedoStore ts1;
  TorpedoStore ts2;

  @BeforeEach
  public void init(){  
   
    
    ts1 = mock(TorpedoStore.class);
    ts2 = mock(TorpedoStore.class);

     this.ship = new GT4500(ts1,ts2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(ts1.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    verify(ts1,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(ts1.fire(1)).thenReturn(true);
    when(ts2.fire(1)).thenReturn(true);
    when(ts1.isEmpty() && ts2.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    //assertEquals(true, result);
    verify(ts1,times(1)).fire(1);
  }
  //1
  @Test
  public void fireTorpedo_Only_One(){
    when(ts1.getTorpedoCount()).thenReturn(1);
    when(ts2.getTorpedoCount()).thenReturn(1);

    ship.fireTorpedo(FiringMode.SINGLE);

    //when(ts1.isEmpty() || ts2.isEmpty() ).thenReturn(true);

    verify(ts1,times(1)).fire(1);
    verify(ts2,times(0)).fire(1);

  }
  //2
  @Test
  public void fireTorpedo_empty(){
    when(ts1.getTorpedoCount()).thenReturn(1);
    when(ts2.getTorpedoCount()).thenReturn(1);

    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    //when(ts1.isEmpty() || ts2.isEmpty() ).thenReturn(true);

    verify(ts1,times(1)).fire(1);
    verify(ts2,times(1)).fire(1);

  }
  //5 folyamatban
  @Test
  public void fireTorpedo_all_empty(){
    when(ts1.getTorpedoCount()).thenReturn(1);
    when(ts2.getTorpedoCount()).thenReturn(1);

    ship.fireTorpedo(FiringMode.ALL);
    //ship.fireTorpedo(FiringMode.SINGLE);

    //when(ts1.isEmpty() || ts2.isEmpty() ).thenReturn(true);

    verify(ts1,times(1)).fire(1);
    verify(ts2,times(1)).fire(1);
    //verify(ts1,times(1)).getTorpedoCount(0);

  }

}
