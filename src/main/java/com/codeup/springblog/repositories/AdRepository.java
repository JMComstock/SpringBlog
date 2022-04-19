package com.codeup.springblog.repositories;

import com.codeup.springblog.services.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {

    Ad findByTitle (String title); // SELECT * FROM ads where title = ?;
    Ad findFirstByTitle (String title); // SELECT * FROM ads WHERE title = ? LIMIT 1;

}
