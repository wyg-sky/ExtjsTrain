/*
 * Copyright © 2008, 2012 Pedro Agulló Soliveres.
 * 
 * This file is part of DirectJNgine.
 *
 * DirectJNgine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * Commercial use is permitted to the extent that the code/component(s)
 * do NOT become part of another Open Source or Commercially developed
 * licensed development library or toolkit without explicit permission.
 *
 * DirectJNgine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DirectJNgine.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * This software uses the ExtJs library (http://extjs.com), which is 
 * distributed under the GPL v3 license (see http://extjs.com/license).
 */

package com.ext.direct.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

/**
 * @description : 后台JAVA方法，必须要声明为@DirectMethod
 * （@DirectPollMethod 或者 其它的@Direct方法），才会被自动生成到指定位置的*.js中.
 *  (js生成位置可以指定，一般生成在部署服务器Tomcat项目文件下)
 * 前台Js文件，引入这些自动生成的js文件就可以通过Extjs中Direct类来调用后台java方法。
 * 后台java自动生成js文件，是通过directjngine这个类库的支持，在Web.xml中配置生成的。
 * @author WangYG
 * @param data
 * @return
 */
public class TestAction {
   //@DirectMethod注解：表明这个方法会被客户端调用，这个方法会被DirectJNgine引擎自动生成客户端代码
  //EirectJNgine还有其他注解参数,自行了解。
  @DirectMethod
  public String doEcho( String data ) {
      //普通的@DirectMethod方法接受参数
      //可以直接通过形参的方式接受参数。
    return data;
  }
  
  @DirectMethod
  public double multiply( String num ) {
    double num_ = Double.parseDouble(num);
    return num_ * 8.0;
  }
  
  public static class Data {
    public String firstName;
    public String lastName;
    public int age;
  }
  
  @DirectMethod
  public String showDetails( Data data ) {
    return "Hi " + data.firstName + "<font size='2' color='red'>·</font>" + data.lastName + ", you are " + data.age + " years old.";
  }
  @DirectMethod
  public List<Map<String,Object>> showListDetails(String data,Object bb,int num){
      List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
      for(int i=1; i<=10; i++){
          Map<String,Object> map = new HashMap<String,Object>();
          map.put("firstName", "firstName_" + i);
          map.put("lastName", "firstName_" + i);
          map.put("age", i);
          dataList.add(map);
      }
      return dataList;
  }
  
  public static class Node {
    public String id;
    public String text;
    public boolean leaf;
  }
  
  @DirectMethod 
  public List<Node> getTree( String id) {
    List<Node> result = new ArrayList<Node>();
    if( id.equals("root")) {
      for( int i = 1; i <= 5; ++i ) {
        Node node = new Node();
        node.id = "n" + i;
        node.text = "Node " + i;
        node.leaf = false;
        result.add(node);
      }
    }
    else if( id.length() == 2) {
      String num = id.substring(1);
      for( int i = 1; i <= 5; ++i ) {
        Node node = new Node();
        node.id = id + i;
        node.text = "Node " + num + "." + i;
        node.leaf = true;
        result.add(node);
      }
    }
    return result;
  }
  
  public static class GridRow {
    public GridRow( String name, int turnover) {
      this.name = name;
      this.turnover = turnover;
    }
    
    public String name;
    public int turnover;
  }
  
  public static class SortInfo {
    public String property;
    public String direction;
  }
  
  @DirectMethod
  public List<GridRow> getGrid( JsonArray params ) {
    // We know this is the structure, but the 'right' way to do this is
    // to define a Java class that maps the information we are receiving
    JsonObject sortInfo = (JsonObject)params.get(0).getAsJsonObject().get("sort").getAsJsonArray().get(0);
    
    assert sortInfo != null;
    List<GridRow> data = new ArrayList<GridRow>();
    String field = sortInfo.get("property" ).getAsString();
    String direction = sortInfo.get("direction" ).getAsString();
    
    if( field.equals("name")) {
      data.add( new GridRow("ABC Accounting", 50000));
      data.add( new GridRow("Ezy Video Rental", 106300));
      data.add( new GridRow("Greens Fruit Grocery", 120000));
      data.add( new GridRow("Icecream Express", 73000));
      data.add( new GridRow("Ripped Gym", 88400));
      data.add( new GridRow("Smith Auto Mechanic", 222980));
    }
    else {
      data.add( new GridRow("ABC Accounting", 50000));
      data.add( new GridRow("Icecream Express", 73000));
      data.add( new GridRow("Ripped Gym", 88400));
      data.add( new GridRow("Ezy Video Rental", 106300));
      data.add( new GridRow("Greens Fruit Grocery", 120000));
      data.add( new GridRow("Smith Auto Mechanic", 222980));
    }
    
    if( direction.equals( "DESC")) {
      Collections.reverse(data);
    }

    return data;
  }  
}
