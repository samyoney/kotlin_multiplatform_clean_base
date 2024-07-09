//
//  SharedSplashViewModel.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/04.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

class SharedSplashVM: SharedViewModel<SplashViewModel> {
    @Published var loadingState: SwiftLoadingState<SplashState> = .idle
    
    override init() {
        super.init()
        shared.onSharedDataListener { state in
            if let state = state {
                self.loadingState = state.asSwiftState()
            }
        }
    }
}

