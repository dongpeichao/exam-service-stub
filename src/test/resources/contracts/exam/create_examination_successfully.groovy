package exam

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description '''
Represents creating a new examination

given:
    Valid paper info  and Some blank quizzes
when:
    a teacher assemble a new examination
then:
    this teacher should get a valid examinationId
'''

    request {
        url "/examinations"
        method POST()
        headers {
            contentType applicationJson()
        }
        body(
                teacherId: '8jk4l-k0d9ie7-4jk89l-t88ijj6-h8i9040',
                paperId: '8jk4l-k0d9ie7-4jk89l-t88irr7-h8i9040',
                duration: 120,
                quizzes: [
                        [
                                id   : '8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl1',
                                score: 5
                        ],
                        [
                                id   : '8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl2',
                                score: 10
                        ],
                        [
                                id   : '8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl3',
                                score: 20
                        ],
                        [
                                id   : '8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl4',
                                score: 15
                        ],
                        [
                                id   : '8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl5',
                                score: 20
                        ],
                        [
                                id   : '8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl6',
                                score: 30
                        ]

                ],
        )
        bodyMatchers {
            jsonPath('$.teacherId', byRegex('[a-zA-Z-0-9]{36}'))
            jsonPath('$.paperId', byRegex('[a-zA-Z-0-9]{36}'))
            jsonPath('$.duration', byRegex('.{1,1000}'))
            jsonPath('$.quizzes', byType {minOccurrence(0)})
            jsonPath("\$.['quizzes'].['id']", byRegex('[a-zA-Z-0-9]{36}'))
            jsonPath("\$.['quizzes'].['score']", byRegex('100|[1-9][0-9]|[1-9]'))
        }
    }

    response {
        status CREATED()
        headers {
            contentType applicationJson()
        }
        body(
                examinationId: $(producer(regex('[a-zA-Z-0-9]{36}')), consumer('8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl4'))
        )
    }
}