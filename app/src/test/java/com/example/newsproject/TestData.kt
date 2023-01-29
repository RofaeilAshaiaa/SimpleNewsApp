package com.example.newsproject

import com.example.newsproject.data.models.Article
import com.example.newsproject.data.models.ArticlesResponse
import com.example.newsproject.data.models.Source

fun createArticles() = arrayListOf(
    Article(
        source = createSource(),
        author = "Nils Prat",
        title = "U.S. says it 'hacked the hackers' to bring down ransomware gang, helping 300 victims - Reuters",
        description = "The FBI revealed on Thursday it had secretly hacked and disrupted a prolific ransomware gang called Hive, a maneuver that allowed the bureau to thwart the group from collecting more than \$130 million in ransomware demands from more than 300 victims.",
        urlToImage = "https://cdn.mos.cms.futurecdn.net/6VjPtVbzAwjhdnDcymKuL-1200-80.jpg",
        url = "https://www.reuters.com/world/us/announcement-posted-hive-ransomware-groups-site-says-it-has-been-seized-by-fbi-2023-01-26/",
        publishedAt = "2023-01-26T20:28:00Z",
        content = "WASHINGTON, Jan 26 (Reuters) - The FBI revealed on Thursday it had secretly hacked and disrupted a prolific ransomware gang called Hive, a maneuver that allowed the bureau to thwart the group from co… [+3483 chars]"
    ),
    Article(
        source = createSource(),
        author = "Brett Tingley",
        title = "Watch asteroid 2023 BU pass close by Earth today in this free webcast - Space.com",
        description = "The asteroid will pass between Earth and satellites in geostationary orbit.\$130 million in ransomware demands from more than 300 victims.",
        urlToImage = "https://cdn.mos.cms.futurecdn.net/6VjPtVbzAwjhdnDcymKuL-1200-80.jpg",
        url = "https://www.reuters.com/world/us/announcement-posted-hive-ransomware-groups-site-says-it-has-been-seized-by-fbi-2023-01-26/",
        publishedAt = "2023-01-26T20:28:00Z",
        content = "Update for 3:45 p.m. ET: The Virtual Telescope Project's webcast of asteroid 2023 BU is now scheduled for 4:15 p.m. EST (2115 GMT) due to clouds. A newly discovered asteroid will come very close to… [+3342 chars]"
    )
)

fun createSource() = Source(id = "reuters", name = "Reuters")

fun createArticlesResponse() = ArticlesResponse(
    status = "ok",
    totalResults = 2,
    articles = createArticles(),
    message = null
)