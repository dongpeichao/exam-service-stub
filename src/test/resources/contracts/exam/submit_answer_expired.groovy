package exam

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description '''
Represents submit a answer sheet

given:
    Valid answer sheet
when:
    a student submit answer expired
then:
    this student should get an 400 error
'''

    request {
        url $(consumer("/examinations/${regex('[a-zA-Z-0-9]{36}')}/answer-sheet/${regex('[a-zA-Z-0-9]{36}')}"),
                producer("/examinations/9idk4-lokfu-jr874j3-h8d9j4-hor82kd77/answer-sheet/9idk4-lokfu-jr874j3-h8d9j4-ho7kdl009"))
        method PUT()
        body(
                studentId: regex('[a-zA-Z-0-9]{36}'),
                answer: anyNonBlankString(),
                startTime: '2020-06-29T12:00:00',
                submitTime: '2020-06-29T15:00:00'
        )
    }

    response {
        status BAD_REQUEST()
    }
}