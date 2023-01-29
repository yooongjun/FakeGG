package LOL.demo.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

@RestController
@RequestMapping("/riot/api")
public class RiotApiController {

    //API KEY 
    private final String RiotKey = "RGAPI-449595b6-1fc8-4fe7-b655-07dc107bf4db";

    @GetMapping("/summoner")
    public String SUMMONER_V4(@RequestParam String userName){

        try {
            // userName 한글 인코딩
            userName = URLEncoder.encode(userName, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 공백을 %20으로 변경
        userName.replace("+", "%20");

        // RestTemplate, HttpHeaders 객체 생성
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // GET 요청 헤더 값으로 API KEY를 넘겨줌.
        headers.set("X-Riot-Token", RiotKey);

        //API키 정보 헤더 추가
        HttpEntity<String> entity = new HttpEntity<String>("parameter", headers);

        URI requestUrl = URI.create("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + userName);

        ResponseEntity<SummonerDTO> result2 = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, SummonerDTO.class);

        String name = result2.getBody().getName();

        return "유저 이름: " + name;
    }

}
