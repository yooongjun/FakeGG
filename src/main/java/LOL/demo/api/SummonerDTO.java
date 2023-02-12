package LOL.demo.api;

import lombok.Getter;

// 소환서 조회 정보 담는 객체
@Getter
public class SummonerDTO {

    String accountId;
    int profileIconId;
    long revisionDate;
    String name;
    String id;
    String puuid;
    String summonerLevel;

}
