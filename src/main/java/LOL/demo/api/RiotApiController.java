package LOL.demo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/riot/api")
public class RiotApiController {

    static HttpHeaders headers = new HttpHeaders();
    static HttpEntity<String> entity;
    //API KEY

    @GetMapping("/summoner")
    public String SUMMONER_V4(@RequestParam String userName ){

        try {
            // userName 한글 인코딩
            userName = URLEncoder.encode(userName, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 공백 인코딩
        userName.replace("+", "%20");

        // HttpHeader 설정
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Riot-Token", RiotKey.APIKEY);

        entity = new HttpEntity<>(headers);

        // 1) UUID 얻기
        String UUID = getUUID(userName);

        // 2)
        List<String> matchId = getMatchId(UUID);
        return matchId.toString();
    }

    private static String getUUID(String userName) {

        log.info("userName : {}" , userName);

        // RestTemplate, HttpHeaders 객체 생성
        RestTemplate restTemplate = new RestTemplate();

        // 요청할 URL 지정
        URI requestUrl = URI.create("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + userName);

        ResponseEntity<SummonerDTO> result2 = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, SummonerDTO.class);

        return result2.getBody().getPuuid();
    }

    private static List<String> getMatchId(String UUID){

        RestTemplate restTemplate = new RestTemplate();

        // 20개의 match 조회
        URI requestUrl = URI.create("https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + UUID + "/ids?start=0&count=20");

        List<String> matchList = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, List.class).getBody();
        return matchList;
    }


}
