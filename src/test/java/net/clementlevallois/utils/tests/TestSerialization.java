/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.clementlevallois.utils.tests;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.clementlevallois.utils.DebuggingOutputStream;
import net.clementlevallois.utils.Multiset;
import net.clementlevallois.utils.Serializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author LEVALLOIS
 */

public class TestSerialization {

  @Test
  public void testToByteArray() {
      Multiset ms =new Multiset();
      ms.addOne("allo");
      ms.addOne("allo");
      ms.addOne("allo");
      Assertions.assertEquals(1, ms.getSize());
//      Assertions.assertEquals(3, ms.toListOfAllOccurrences().size());
      byte[] byteArraySerializerForAnyObject = Serializer.byteArraySerializerForAnyObject(ms);
      Assertions.assertNotNull(byteArraySerializerForAnyObject);
  }
} 
