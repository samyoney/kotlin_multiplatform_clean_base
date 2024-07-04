//
//  Loading+Extension.swift
//  iosApp
//
//  Created by グェン・ホン・ソン on 2024/07/03.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Shared

extension View {
    
    func observeLoadingState<T>(
        _ loadingState: Binding<SwiftLoadingState<T>>,
        onLoaded: @escaping (T?) -> Void = { _ in }
    ) -> some View {
        self.onError(loadingState, String())
            .onLoading(loadingState)
            .onLoaded(loadingState.wrappedValue, onNext: onLoaded)
    }
    
    func onError<T>(_ loadingState: Binding<SwiftLoadingState<T>>, _ title: String = String()) -> some View {
        var errorMsg = appText.errorApiMessage()
        let isPresented = Binding<Bool>(
            get: {
                if case .error(let msg) = loadingState.wrappedValue {
                    errorMsg = msg
                    return true
                } else {
                    return false
                }
            },
            set: { newValue in
                if !newValue {
                    loadingState.wrappedValue = .idle
                }
            }
        )
        return self.modifier(ErroDialogModifier(isPresented: isPresented, title: title, message: errorMsg, buttonTitle: appText.ok(), buttonAction: {
            loadingState.wrappedValue = .idle
        }))
    }
    
    
    func onLoading<T>(_ loadingState: Binding<SwiftLoadingState<T>>) -> some View {
        let isPresented = Binding<Bool>(
            get: {
                if case .loading = loadingState.wrappedValue {
                    return true
                } else {
                    return false
                }
            },
            set: { _ in
            }
        )
        return self.modifier(ProgressIndicatorModifier(isPresented: isPresented))
    }
    
    func onLoaded<T>(_ loadingState: SwiftLoadingState<T>, onNext: @escaping (T?) -> Void) -> some View {
        self.onChange(of: loadingState) {
            switch loadingState {
            case .loaded(let data): onNext(data)
            default: break
            }
        }
    }
}
