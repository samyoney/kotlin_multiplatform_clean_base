import SwiftUI
import Shared

struct ContentView: View {
    @StateObject var viewModel = SharedViewModelStoreOwner<SplashViewModel>()
    
    @State private var showContent = false
    var body: some View {
        VStack {
            VStack(spacing: 16) {
                if viewModel.shared.uiState.value is LoadingStateIdle {
                }
            }
            .transition(.move(edge: .top).combined(with: .opacity))
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding().onAppear(perform: {
            Task {
                viewModel.shared.onTriggerEvent(eventType: .InitData())
            }
        })
    }
}



