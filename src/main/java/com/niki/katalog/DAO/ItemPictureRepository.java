package com.niki.katalog.DAO;

import com.niki.katalog.entity.ItemPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemPictureRepository extends JpaRepository<ItemPicture, Integer> {

    @Query("from ItemPicture ip where ip.item.id = :itemKey ")
    List<ItemPicture> getByItemId(@Param("itemKey") int theItemId);
}
