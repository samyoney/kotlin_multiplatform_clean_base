//
//  SharedSamVM.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/04.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

extension CourseDto: Identifiable {}
extension StudentDto: Identifiable {}

class SharedSamVM: SharedViewModel<SamViewModel> {
    @Published var listCourse: [CourseDto] = []
    @Published var listStudent: [StudentDto] = []
    @Published var listStudentByCode: [StudentDto] = []
    @Published var isCourseScreen: Bool = true

    override init() {
        super.init()
        shared.onNotifyUIPublisher { state in
            if let state = state {
                self.listCourse = state.listCourse
                self.listStudent = state.listStudent
                self.listStudentByCode = state.listStudentByCode
                self.isCourseScreen = state.isCourseScreen
            }
        }
    }
}
