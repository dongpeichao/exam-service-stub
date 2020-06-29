package exam

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description '''
Represents creating a new answer sheet

given:
    Valid examinationId
when:
    a student begin examination and create a new answer sheet
then:
    this student should get valid answer sheet info
'''

    request {
        url $(consumer("/examinations/${regex('[a-zA-Z-0-9]{36}')}/answer-sheets"),
        producer("/examinations/9idk4-lokfu-jr874j3-h8d9j4-hor82kd77/answer-sheets"))
        method POST()
    }

    response {
        status CREATED()
        headers {
            contentType applicationJson()
        }
        body(
                answerSheetId: '8jk4l-k0d9ie7-4jk89l-t99ijj6-h8i9040',
                examinationId: '8jk4l-k0d9ie7-4jk89k-t99ijj6-h8i9040',
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
            jsonPath('$.answerSheetId', byRegex('[a-zA-Z-0-9]{36}'))
            jsonPath('$.examinationId', byRegex('[a-zA-Z-0-9]{36}'))
            jsonPath('$.duration', byRegex('.{1,1000}'))
            jsonPath('$.quizzes', byType {minOccurrence(0)})
            jsonPath("\$.['quizzes'].['id']", byRegex('[a-zA-Z-0-9]{36}'))
            jsonPath("\$.['quizzes'].['score']", byRegex('100|[1-9][0-9]|[1-9]'))
        }
    }
}