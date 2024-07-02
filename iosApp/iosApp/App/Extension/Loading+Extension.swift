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
        _ loadingState: Binding<LoadingState>,
        onLoaded: @escaping (AnyObject?) -> Void = { _ in }
    ) -> some View {
        self.onError(loadingState, String())
            .onLoading(loadingState)
            .onLoaded(loadingState.wrappedValue, onNext: onLoaded)
    }
    
    func onError(_ loadingState: Binding<LoadingState>, _ title: String = String()) -> some View {
        var errorMsg = appText.errorApiMessage()
        let isPresented = Binding<Bool>(
            get: {
                if let errorState = loadingState.wrappedValue as? LoadingStateError {
                    errorMsg = errorState.mess
                    return true
                } else {
                    return false
                }
            },
            set: { newValue in
                if !newValue {
                    loadingState.wrappedValue = LoadingStateIdle()
                }
            }
        )
        return self.modifier(ErroDialogModifier(isPresented: isPresented, title: title, message: errorMsg, buttonTitle: appText.ok(), buttonAction: {
            loadingState.wrappedValue = LoadingStateIdle()
        }))
    }
    
    
    func onLoading(_ loadingState: Binding<LoadingState>) -> some View {
        let isPresented = Binding<Bool>(
            get: {
                if let loadingState = loadingState.wrappedValue as? LoadingStateLoading {
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
    
    func onLoaded(_ loadingState: LoadingState?, onNext: @escaping (AnyObject?) -> Void) -> some View {
        if let loadingState = loadingState as? LoadingStateLoaded<AnyObject> {
            if let data = loadingState.data {
                onNext(data)
            }
        }
        return EmptyView()
    }
}
