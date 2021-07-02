package com.steammer.domain.gameTag;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameTagRepository extends JpaRepository<GameTag, Long> {

}
