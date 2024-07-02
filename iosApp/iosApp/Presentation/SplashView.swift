//
//  SplashView.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/03.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct SplashView: View {
    @EnvironmentObject var appRouter: SwiftAppRouter

    @StateObject var viewModel = SharedViewModel<SplashViewModel>()
    
    var body: some View {
        ZStack {
            Color("OrangeBgColor")
                .ignoresSafeArea()
            Image("Logo")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .padding(100)
        }.onAppear {
            viewModel.shared.onTriggerEvent(eventType: .InitData())
        }.onLoaded(viewModel.getUIState()) {_ in
            print("Loaded")
        }
    }
}
