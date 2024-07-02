//
//  AppRouter.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/03.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

class SwiftAppRouter: ObservableObject {
    enum NavigationType {
        case push
        case sheet
        case fullScreenCover
    }
    
    enum Route: Hashable, Identifiable {
        case splash
        case login
        case sam
        
        var id: Route {
            self
        }
    }
    @Published private var rootNav = Route.splash

    @Published var path: NavigationPath = NavigationPath()
    @Published var presentingSheet: Route?
    @Published var presentingFullScreenCover: Route?
    @Published var isPresented: Binding<Route?>
    
    @ViewBuilder func view(for route: Route) -> some View {
        switch route {
        case .splash:
            SplashView()
        case .login:
            EmptyView()
        case .sam:
            EmptyView()
        }
    }
    
    @ViewBuilder func getRootview() -> some View {
        switch rootNav {
        case .splash:
            SplashView()
        case .login:
            EmptyView()
        case .sam:
            EmptyView()
        }
    }

    func router(navigationType: NavigationType) -> SwiftAppRouter {
        switch navigationType {
        case .push:
            return self
        case .sheet:
            return SwiftAppRouter(
                isPresented: Binding(
                    get: { self.presentingSheet },
                    set: { self.presentingSheet = $0 }
                )
            )
        case .fullScreenCover:
            return SwiftAppRouter(
                isPresented: Binding(
                    get: { self.presentingFullScreenCover },
                    set: { self.presentingFullScreenCover = $0 }
                )
            )
        }
    }
    
    func dismiss() {
        if !path.isEmpty {
            path.removeLast()
        } else if presentingSheet != nil {
            presentingSheet = nil
        } else if presentingFullScreenCover != nil {
            presentingFullScreenCover = nil
        } else {
            isPresented.wrappedValue = nil
        }
    }
    
    init(isPresented: Binding<Route?> = .constant(.splash)) {
        self.isPresented = isPresented
    }
    
    func setRootview(route: Route) {
        self.rootNav = route
    }
    
    func presentSheet(_ route: Route) {
        self.presentingSheet = route
    }
    
    func presentFullScreen(_ route: Route) {
        self.presentingFullScreenCover = route
    }
    
    func navigateTo(_ appRoute: Route) {
        path.append(appRoute)
    }
    
    func navigateBack() {
        path.removeLast()
    }
    
    func popToRoot() {
        path.removeLast(path.count)
    }
}

