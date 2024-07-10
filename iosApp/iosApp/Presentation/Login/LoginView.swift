//
//  LoginView.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/04.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct LoginView: View {
    @EnvironmentObject var appRouter: SwiftAppRouter
    
    @StateObject var viewModel: SharedLoginVM = .init()
    
    @Environment(\.dismiss) private var dismiss
    
    @FocusState private var focusedField: Field?
    
    enum Field: Hashable {
        case username, password, name
    }
    
    var body: some View {
        NavigationView {
            VStack {
                if viewModel.isRegisterScreen {
                    RegisterContentView(viewModel: viewModel)
                } else {
                    LoginContentView(viewModel: viewModel)
                }
            }.onChange(of: viewModel.loadingState == .loaded(nil)) {
                appRouter.navigateTo(.sam)
            }
            .navigationBarTitleDisplayMode(.inline)
            .toolbarBackground(Color(R.color.statusBarColor), for: .navigationBar)
            .toolbarBackground(.visible, for: .navigationBar)
            .toolbar {
                ToolbarItem(placement: .principal) {
                    Text(viewModel.isRegisterScreen ? appText.registerNavTab() : appText.loginNavTab())
                        .font(.system(size: 24, weight: .bold))
                        .foregroundColor(Color(R.color.orangeBgColor))
                }
            }
            .onTapGesture {
                hideKeyboard()
            }
            .observeLoadingState($viewModel.loadingState) { _ in
                appRouter.setRootview(route: .sam)
            }
        }
    }
    
    func hideKeyboard() {
        focusedField = nil
    }
}

struct LoginContentView: View {
    @ObservedObject var viewModel: SharedLoginVM
    
    var body: some View {
        VStack {
            ExtraLargeSpacer()
            LoginInputField(
                value: $viewModel.username, label: appText.name(),
                placeholder: appText.placeHolder(),
                helperText: appText.description_()
            ).onChange(of: viewModel.username) { _, newValue in
                viewModel.onTriggerEvent(eventType: .InputUsername(text: newValue))
            }
            ExtraLargeSpacer()
            LoginInputField(
                value: $viewModel.password, label: appText.password(),
                placeholder: appText.placeHolder(),
                helperText: appText.description_()
            ).onChange(of: viewModel.password) { _, newValue in
                viewModel.onTriggerEvent(eventType: .InputPassword(text: newValue))
            }
            ExtraLargeSpacer()
            LoginButton(title: appText.login()) {
                viewModel.onTriggerEvent(eventType: .Login())
            }
            ExtraLargeSpacer()
            LoginButton(title: appText.goToRegister()) {
                viewModel.onTriggerEvent(eventType: .ChangeLoginMode())
            }
        }
        .padding(.horizontal, 16)
    }
}

struct RegisterContentView: View {
    @ObservedObject var viewModel: SharedLoginVM
    
    var body: some View {
        VStack {
            ExtraLargeSpacer()
            LoginInputField(
                value: $viewModel.username, label: appText.name(),
                placeholder: appText.placeHolder(),
                helperText: appText.description_()
            ).onChange(of: viewModel.username) { _, newValue in
                viewModel.onTriggerEvent(eventType: .InputUsername(text: newValue))
            }
            ExtraLargeSpacer()
            LoginInputField(
                value: $viewModel.password, label: appText.password(),
                placeholder: appText.placeHolder(),
                helperText: appText.description_()
            ).onChange(of: viewModel.password) { _, newValue in
                viewModel.onTriggerEvent(eventType: .InputPassword(text: newValue))
            }
            ExtraLargeSpacer()
            LoginInputField(
                value: $viewModel.name, label: appText.name(),
                placeholder: appText.placeHolder(),
                helperText: appText.description_()
            ).onChange(of: viewModel.name) { _, newValue in
                viewModel.onTriggerEvent(eventType: .InputName(text: newValue))
            }
            ExtraLargeSpacer()
            LoginBirthButton(birth: $viewModel.birth) { year, month in
                viewModel.onTriggerEvent(eventType: .InputBirth(year: year, month: month))
            }
            ExtraLargeSpacer()
            LoginButton(title: appText.register()) {
                viewModel.onTriggerEvent(eventType: .Register())
            }
            ExtraLargeSpacer()
            LoginButton(title: appText.backToLogin()) {
                viewModel.onTriggerEvent(eventType: .ChangeLoginMode())
            }
        }
        .padding(.horizontal, 16)
    }
}
