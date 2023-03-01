package Appl.project.Service;

import Appl.project.Exceptions.ItemNotFoundException;
import Appl.project.Model.Mobile;
import Appl.project.Repository.MobileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Service

public class MobileServiceImpl implements MobileService {
    @Autowired
    MobileRepo mobileRepo;

    @Override
    public List<Mobile> addMobile(Iterable<Mobile> product) {
        System.out.println("Adding product");
        return mobileRepo.saveAll(product);
    }

    @Override
    public Mobile getMobileById(Integer mobileId) {
        return mobileRepo.findById(mobileId).orElseThrow(
                () -> new ItemNotFoundException("No such item ("+mobileId+") found"));
    }

    @Override
    public ArrayList<Mobile> getMobileByName(String name){
        List<Mobile> whole =mobileRepo.findAll();
        ArrayList<Mobile> ans = new ArrayList<>();
        if (whole.isEmpty()){
            throw (new ItemNotFoundException("No Entries in the table"));
        }
        else {
            for (Mobile each : whole) {
                if (each.getModelName().toLowerCase().contains(name)) {
                    ans.add(each);
                }
            }
        }
        return ans;
    }

    @Override
    public List<Mobile> getMobiles() {
        return mobileRepo.findAll();
    }

    @Override
    public Mobile updateMobile(Integer mobileId, Mobile obj) throws ItemNotFoundException {
        System.out.println("Updating Product");
        Mobile product = mobileRepo.findById(mobileId).orElseThrow(
                () -> new ItemNotFoundException("No such item ("+mobileId+") found"));
        product.setHasBluetoothConnectivity(Objects.isNull(obj.isHasBluetoothConnectivity())?product.isHasBluetoothConnectivity():obj.isHasBluetoothConnectivity());
        product.setHasNetworkConnectivity(Objects.isNull(obj.isHasNetworkConnectivity())?product.isHasNetworkConnectivity():obj.isHasNetworkConnectivity());
        product.setModelName(Objects.isNull(obj.getModelName())?product.getModelName():obj.getModelName());
        product.setOsType(Objects.isNull(obj.getOsType())?product.getOsType():obj.getOsType());
        product.setPrice(Objects.isNull(obj.getPrice())?product.getPrice():obj.getPrice());
        product.setQuantity(Objects.isNull(obj.getQuantity())?product.getQuantity():obj.getQuantity());

        return mobileRepo.save(product);
    }

    @Override
    public void deleteMobile(Integer mobileId) throws ItemNotFoundException {
       System.out.println("deleting product by id");
        Mobile product = mobileRepo.findById(mobileId).orElseThrow(
                () -> new ItemNotFoundException("No such item found by id = "+mobileId)
        );
        mobileRepo.delete(product);
    }
}
