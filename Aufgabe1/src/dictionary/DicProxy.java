/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

/**
 *
 * @author  Tobi, Chris
 */
public class DicProxy<K, V> {

    private Dictionary<K, V> dic;
    
    public DicProxy(Dictionary<K,V> dict){
        this.dic = dict;
    }
    
    public Dictionary<K,V> getDic(){
        return dic;
    }
    
    public void setDic(Dictionary<K,V> dict){
        this.dic = dict;
        
    }
    
}
