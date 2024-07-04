import SwiftUI
import Shared

struct SplashView: View {
    @EnvironmentObject var appRouter: SwiftAppRouter
    
    @StateObject var viewModel: SharedSplashVM = .init()
    
    @State private var showContent = false
    var body: some View {
        ZStack {
            Color(R.color.orangeBgColor)
                .ignoresSafeArea()
            Image(R.image.logo)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .padding(100)
        }
        .observeLoadingState($viewModel.loadingState) { loadedData in
            if loadedData?.isNextLogin == true {
                appRouter.setRootview(route: .login)
            } else {
                appRouter.setRootview(route: .sam)
            }
        }
        .onAppear(perform: {
            viewModel.shared.onTriggerEvent(eventType: .InitData())
        })
    }
}
