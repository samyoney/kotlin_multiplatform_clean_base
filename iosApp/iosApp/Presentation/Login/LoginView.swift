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
                    RegisterContentView(username: $viewModel.username, password: $viewModel.password, name: $viewModel.name, birth: $viewModel.birth, registerAction: {
                        viewModel.shared.onTriggerEvent(eventType: .Register())
                    }, backToLoginAction: {
                        viewModel.shared.onTriggerEvent(eventType: .ChangeLoginMode())
                    })
                } else {
                    LoginContentView(username: $viewModel.username, password: $viewModel.password, loginAction: {
                        viewModel.shared.onTriggerEvent(eventType: .Login())
                    }, goToRegisterAction: {
                        viewModel.shared.onTriggerEvent(eventType: .ChangeLoginMode())
                    })
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
    @Binding var username: String
    @Binding var password: String
    
    var loginAction: () -> Void
    var goToRegisterAction: () -> Void
    
    var body: some View {
        VStack {
            ExtraLargeSpacer()
            LoginInputField(
                value: $username, label: appText.name(),
                placeholder: appText.placeHolder(),
                helperText: appText.description_()
            )
            ExtraLargeSpacer()
            LoginInputField(
                value: $password, label: appText.password(),
                placeholder: appText.placeHolder(),
                helperText: appText.description_()
            )
            ExtraLargeSpacer()
            LoginButton(title: appText.login()) {
                loginAction()
            }
            ExtraLargeSpacer()
            LoginButton(title: appText.goToRegister()) {
                goToRegisterAction()
            }
        }
        .padding(.horizontal, 16)
    }
}

struct RegisterContentView: View {
    @Binding var username: String
    @Binding var password: String
    @Binding var name: String
    @Binding var birth: String
    
    var registerAction: () -> Void
    var backToLoginAction: () -> Void
    
    var body: some View {
        VStack {
            ExtraLargeSpacer()
            LoginInputField(
                value: $username, label: appText.name(),
                placeholder: appText.placeHolder(),
                helperText: appText.description_()
            )
            ExtraLargeSpacer()
            LoginInputField(
                value: $password, label: appText.password(),
                placeholder: appText.placeHolder(),
                helperText: appText.description_()
            )
            ExtraLargeSpacer()
            LoginInputField(
                value: $name, label: appText.name(),
                placeholder: appText.placeHolder(),
                helperText: appText.description_()
            )
            ExtraLargeSpacer()
            LoginBirthButton(birth: $birth) { year, month in
            }
            ExtraLargeSpacer()
            LoginButton(title: appText.register()) {
                registerAction()
            }
            ExtraLargeSpacer()
            LoginButton(title: appText.backToLogin()) {
                backToLoginAction()
            }
        }
        .padding(.horizontal, 16)
    }
}
