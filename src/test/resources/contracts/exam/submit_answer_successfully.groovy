package exam

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description '''
Represents submit a answer sheet

given:
    Valid answer sheet
when:
    a student submit answer in duration
then:
    this student should get the answer sheet info
'''

    request {
        url $(consumer("/examinations/${regex('[a-zA-Z-0-9]{36}')}/answer-sheet/${regex('[a-zA-Z-0-9]{36}')}"),
                producer("/examinations/9idk4-lokfu-jr874j3-h8d9j4-hor82kd77/answer-sheet/9idk4-lokfu-jr874j3-h8d9j4-ho7kdl009"))
        method PUT()
        body(
                studentId: regex('[a-zA-Z-0-9]{36}'),
                answer: anyNonBlankString(),
                startTime: '2020-06-29T12:00:00',
                submitTime: '2020-06-29T13:15:00',
        )
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
                answerSheetId: regex('[a-zA-Z-0-9]{36}'),
                examinationId: regex('[a-zA-Z-0-9]{36}'),
                studentId: fromRequest().body('$.studentId'),
                answer: fromRequest().body('$.answer'),
                startTime: fromRequest().body('$.startTime'),
                submitTime: fromRequest().body('$.submitTime')
        )
    }
}