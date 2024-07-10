//
//  SharedSplashViewModel.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/04.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

class SharedSplashVM: BaseSharedVM<LoadingState, SplashEvent> {
    @Published var loadingState: SwiftLoadingState<SplashState> = .idle

    override func createSharedVM() -> SplashViewModel {
        return SwiftKoin.shared.inject()
    }
    
    override func onNotifyPublisherDidChange(state: any LoadingState) {
        loadingState = state.asSwiftState()
    }
}

