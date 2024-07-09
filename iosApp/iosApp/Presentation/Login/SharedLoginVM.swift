//
//  SharedLoginVM.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/04.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

class SharedLoginVM: SharedViewModel<LoginViewModel> {
    @Published var username: String = String()
    @Published var password: String = String()
    @Published var name: String = String()
    @Published var birth: String = String()
    @Published var isRegisterScreen: Bool = false
    @Published var loadingState: SwiftLoadingState<KotlinNothing> = .idle

    override init() {
        super.init()
        shared.onSharedDataListener { state in
            self.refreshUIPublisher(state)
        }
    }
    
    func refreshUIPublisher(_ state: LoginState? = nil) {
        let state = state ?? shared.getSharedData()
        DispatchQueue.main.async { [weak self] in
            self?.username = state.username
            self?.password = state.password
            self?.name = state.name
            self?.birth = state.birth
            self?.isRegisterScreen = state.isRegisterScreen
            self?.loadingState = state.loadingState.asSwiftState()
        }
    }
}
