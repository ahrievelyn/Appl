package Appl.project.Service;

import Appl.project.Exceptions.ItemNotFoundException;
import Appl.project.Model.Mobile;

import java.util.ArrayList;
import java.util.List;
public interface MobileService {
    List<Mobile> addMobile(Iterable<Mobile> mobile);
    Mobile getMobileById(Integer mobileId) throws ItemNotFoundException;
    ArrayList<Mobile> getMobileByName(String name) throws ItemNotFoundException;
    List<Mobile> getMobiles();
    Mobile updateMobile(Integer productId, Mobile obj)  throws ItemNotFoundException;
    void deleteMobile(Integer mobileId) throws ItemNotFoundException;
}
