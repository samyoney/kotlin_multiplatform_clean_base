//
//  SamView.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/04.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct SamView: View {
    @EnvironmentObject var router: SwiftAppRouter
    
    @StateObject var viewModel: SharedSamVM = .init()
    
    var body: some View {
        NavigationView {
            VStack {
                if viewModel.isCourseScreen {
                    CourseListView(courses: viewModel.listCourse) { id in
                        viewModel.onTriggerEvent(eventType: .SelectCourse(id: id))
                    }
                } else {
                    Spacer(minLength: 30)
                    VStack {
                        Text(appText.totalStudentsTitle())
                            .font(.title)
                        Spacer(minLength: 20)
                        StudentListView(isRegistered: false, students: viewModel.listStudent) { id in
                            viewModel.onTriggerEvent(eventType: .RegisterStudent(id: id))
                        }
                    }
                    Spacer(minLength: 30)
                    VStack {
                        Text(appText.enrolledStudent())
                            .font(.title)
                        Spacer(minLength: 20)
                        StudentListView(isRegistered: true, students: viewModel.listStudentByCode) { id in
                            viewModel.onTriggerEvent(eventType: .RemoveStudent(id: id))
                        }
                    }
                }
            }
            .navigationBarTitleDisplayMode(.inline)
            .toolbarBackground(Color(R.color.statusBarColor), for: .navigationBar)
            .toolbarBackground(.visible, for: .navigationBar)
            .navigationBarBackButtonHidden(true)
            .toolbar {
                ToolbarItem(placement: .principal) {
                    Text(viewModel.isCourseScreen ? appText.courseNavTab() : appText.studentNavTab())
                        .font(.system(size: 24, weight: .bold))
                        .foregroundColor(Color(R.color.orangeBgColor))
                }
                ToolbarItem(placement: .topBarLeading) {
                    if !viewModel.isCourseScreen {
                        Button {
                            viewModel.onTriggerEvent(eventType: .Back())
                        } label: {
                            Image(systemName: "chevron.left")
                                .foregroundColor(Color(R.color.orangeBgColor))
                        }
                    }
                }
            }
        }
        .onAppear {
            viewModel.onTriggerEvent(eventType: .InitData())
        }
        
    }
}

